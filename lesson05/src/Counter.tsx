
import React, {useState} from "react";


export default function Counter({ label }: { label: string }) {
    const [observedCount, setCount] = useState<number>(0);
    
    console.log("render", { label: label, observedCount: observedCount });
    return (
        <div>
            <h2>{label}</h2>
            <p>Counter: {observedCount}
                <input type="range" value={observedCount} min="0" max="10" readOnly/>
            </p>

            <button onClick={() => setCount(observedCount + 1)}>Up</button>
            {/* <button onClick={() => {
                ++observedCount
                console.log("updateCounter", { label: label, observedCount: observedCount });
             }
            }>Up</button> */}
        </div>
    );
}