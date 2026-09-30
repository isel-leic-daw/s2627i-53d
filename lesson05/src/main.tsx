import { createRoot } from "react-dom/client";
import React from "react";

console.log("main starting");

const root = createRoot(document.getElementById("container")!);

root.render(createList(["Apple", "Banana", "Orange"]))

console.log(document.createElement("p") instanceof HTMLElement)
console.log(React.createElement("p") instanceof HTMLElement)
//document.getElementById("container")!.appendChild(React.createElement("p")) 

function createList(elements: string[]) {
  return (
    <ul>
      {elements.map((it) => (
        <li key={it}>
          <span className="item">{it}</span>
        </li>
      ))}
    </ul>
  );
}