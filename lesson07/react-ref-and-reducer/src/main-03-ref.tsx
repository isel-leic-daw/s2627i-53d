import * as React from "react";
import { RefObject, useEffect, useRef, useState } from "react";
import * as ReactDOM from "react-dom/client";

/**
 * Whenever we click + -> change count -> Render -> eval new msg -> GUI
 */
// function SilentCounter() {
//   const [count, setCount] = useState(0);
//   const msg = " counter = " + count
//   return (
//     <div>
//       <button onClick={() => setCount(count + 1)}>+</button>
//       <button >Update</button>
//       {msg}
//     </div>
//   );
// }

/**
 * Click on + only change count, WITHOUT update GUI.
 * GUI is only updated by click on Update button.
 */
function SilentCounter() {
  const count: RefObject<number> = useRef(0);
  const [msg, setMsg] = useState(" counter = 0");

  const updateCounter = () => setMsg(` counter = ${count.current}`)
  return (
    <>
      <button
        onClick={() => {
          count.current = count.current - 1;
          updateCounter()
        }}
      >
        -
      </button>
      <button
        onClick={() => {
          count.current = count.current + 1;
        }}
      >
        +
      </button>
      <button onClick={updateCounter}>
        Update
      </button>
      {msg}
    </>
  );
}

const root = ReactDOM.createRoot(document.getElementById("container")!);
root.render(
  <div>
    <SilentCounter />
    <SilentCounter />
  </div>
);
