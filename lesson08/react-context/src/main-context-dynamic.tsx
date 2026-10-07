import { createContext, useContext, useState } from 'react';
import type { ReactNode } from 'react';
import * as ReactDOM from "react-dom/client";

type Theme = 'light' | 'dark';

const ThemeContext = createContext<Theme>('light');

export default function MyApp() {
  const [theme, setTheme] = useState<Theme>('dark');

  return (
    <ThemeContext value={theme}>
      <main>
        <label>
          Theme{' '}
          <select
            value={theme}
            onChange={(event) => setTheme(event.target.value as Theme)}
          >
            <option value="light">Light</option>
            <option value="dark">Dark</option>
          </select>
        </label>
        <Form />
        <Form />
      </main>
    </ThemeContext>
  );
}

function Form() {
  return (
    <Panel title="Welcome">
      <Button>Sign up</Button>
      <Button>Log in</Button>
    </Panel>
  );
}

function Panel({ title, children }: { title: string; children: ReactNode }) {
  const theme = useContext(ThemeContext);
  const className = 'panel-' + theme;

  return (
    <section className={className}>
      <h1>{title}</h1>
      {children}
    </section>
  );
}

function Button({ children }: { children: ReactNode }) {
  const theme = useContext(ThemeContext);
  const className = 'button-' + theme;

  return <button className={className}>{children}</button>;
}

const root = ReactDOM.createRoot(document.getElementById('container')!);
root.render(<MyApp />);