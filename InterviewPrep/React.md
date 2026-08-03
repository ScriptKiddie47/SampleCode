 URL : https://www.youtube.com/watch?v=dCLhUialKPQ

`React Component`  - React components are reusable, independent pieces of UI that can be composed together to build complex interfaces. Functional Component should follow Pascal Casing.
`JSX` : JavaScript XML. Lets us write JavaScript Expression

React creates `Virtual DOM`. When an element changes it compares old dom with new and change only the element.
`Props` are the mechanism for passing data from parent to child components — they're React's way of making components configurable and reusable. We should not mutuate props (w.r.t child components)

`State` is what makes React components dynamic. The key insight is that React's rendering is just a function of state — same state always produces the same UI, making it predictable and testable.


`Hooks` are functions that let you plug into React features (state, lifecycle, context) from functional components.
`useState` - useState is essentially React's way of saying — track this value, and whenever it changes, re-render the UI to reflect it
`useEffect` - Runs after every render unless we update dependency array.

`onChange={(e) => setSearchTerm(e.target.value)}`

`Keys` - In React, keys are assigned to list items to help React recognize which elements have changed.

```jsx
function Footer() {
  const currentYear = new Date().getFullYear();
  return (
    <> // Fragments
    {currentYear} // User external variable in JSX
    </>
  );
}
export default Footer;
```