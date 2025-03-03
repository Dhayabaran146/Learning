import React, { useState } from "react";
import Editor from "@monaco-editor/react";
import "./CodeEditor.css";
import Footer from "./header and footer/Footer";

function CodeEditor() {
  const [code, setCode] = useState(`console.log("Hello, world!");`);
  const [output, setOutput] = useState("");
  const [language, setLanguage] = useState("javascript");
  const [loading, setLoading] = useState(false);

  const handleRunCode = async () => {
    setOutput("");
    setLoading(true);

    if (language === "javascript") {
      let consoleOutput = "";
      const originalConsoleLog = console.log;
      console.log = (msg) => (consoleOutput += msg + "\n");

      try {
        eval(code);
        setOutput(consoleOutput || "No output");
      } catch (error) {
        setOutput(`Error: ${error.message}`);
      }

      console.log = originalConsoleLog;
    } else {
      try {
        const response = await fetch("https://your-api.com/run", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ language, code }),
        });

        const data = await response.json();
        setOutput(data.output);
      } catch (error) {
        setOutput("Error running the code.");
      }
    }

    setLoading(false);
  };

  return (
    <div className="code-editor-container">
      <h3>Code Editor ({language.toUpperCase()})</h3>

      <Editor
  height="300px"
  theme="white"  // Ensures dark theme is applied
  language={language}
  value={code}
  options={{
    fontSize: 14,
    minimap: { enabled: false },
    wordWrap: "on",
    lineNumbers: "on",
    automaticLayout: true,  // Fixes rendering issues
  }}
  onChange={(value) => setCode(value)}
/>


      <div className="options">
        <select value={language} onChange={(e) => setLanguage(e.target.value)}>
          <option value="javascript">JavaScript</option>
          <option value="python">Python</option>
          <option value="cpp">C++</option>
        </select>
        <button onClick={handleRunCode} disabled={loading}>
          {loading ? "Running..." : "Run Code"}
        </button>
      </div>

      <div className="output">
        <h3>Output:</h3>
        <pre>{output || "Waiting for execution..."}</pre>
      </div>
      <Footer />
    </div>
  );
}

export default CodeEditor;
