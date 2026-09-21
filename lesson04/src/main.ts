import { createRoot } from "react-dom/client";
import React from "react";

console.log("main starting");

const root = createRoot(document.getElementById("container")!);

root.render(
  React.createElement(
    "div",
    null,
    React.createElement("h3", null, "Hello React"),
    React.createElement(
      "p",
      null,
      "My first ",
      React.createElement("a", { href: "https://react.dev" }, "React"),
      " application"
    )
  )
);
