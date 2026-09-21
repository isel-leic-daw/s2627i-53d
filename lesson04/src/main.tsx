import { createRoot } from "react-dom/client";
import React from "react";

console.log("main starting");

const root = createRoot(document.getElementById("container")!);

root.render(
  <div>
    <h3>Hello React with JSX</h3>
    <p>My first <a href="https://react.dev">React</a> application</p>
  </div>
);