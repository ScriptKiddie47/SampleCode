import { useEffect, useState } from "react";
import Comments from "./Comments";
import Posts from "./Posts";
import ShowData from "./ShowData";
import Users from "./Users";

function App() {
  const [items, setItems] = useState([]);
  const [reqType, setReqTpe] = useState('');
  const API_URL = `https://jsonplaceholder.typicode.com/`;

  useEffect(() => {

    const fetchItems = async () => {
      try {
        const response = await fetch(`${API_URL}${reqType}`);
        if (!response.ok) throw new Error("Did not receve expected data");
        const items = await response.json();
        setItems(items)
      } catch (err) {
        console.log(err);
      }
    }

    fetchItems();
  }, [reqType]);

  return (
    <main>
      <Users setReqTpe={setReqTpe} />
      <Posts setReqTpe={setReqTpe} />
      <Comments setReqTpe={setReqTpe} />
      {items.length ? (
        < ShowData items={items} />
      ) : <p>Empty Map</p>}
    </main>
  );
}

export default App;
