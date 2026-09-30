import { createRoot } from "react-dom/client";
import { createMutationObserver } from "./mutationObserver"
import React from "react";


// The model
type Model = {
    // Just a list of strings
    readonly items: Array<string>;

    // And an incrementing ID
    readonly nextId: number;
};

// Function to generate the next model instance
function nextModel(model: Model): Model {
    // if the size is greater than five, then remove the oldest item
    const prev = model.items.length >= 5
        ? model.items.slice(1)
        : model.items
    // return a new model object
    return {
        nextId: model.nextId + 1,
        items: prev.concat(`item-${model.nextId + 1}`)
    }
}


function Item({ label, key }: { label: string, key: string }): React.ReactElement {
    console.log(`Item with key ${label} created`)
  return (
    <div>
      <p>{label}</p>
      <input type="text" />
    </div>
  );
}

// Function to produce a view given a model,
// where the view is a React virtual node tree represented by the root element
function computeView(model: Model) {
    return (
        <ul>
            {model.items.map((it) => (
                <Item key={it} label={it}/>
            ))}
        </ul>
    );
}

//(() => {
    const root = createRoot(document.getElementById("container")!);
    // The model instance
    let model: Model = {
        nextId: 0,
        items: [],
    };

    // Update the model and re-render every two seconds
    setInterval(() => {
        model = nextModel(model);
        const view = computeView(model);
        root.render(view);
    }, 2000);

    // Just a way to observe mutations to the (real) DOM
    const observer = createMutationObserver();
    observer.observe(document.getElementById("container")!, {
        childList: true,
        subtree: true,
    });
//})();



