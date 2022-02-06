import Header from './Header';
import Additem from './AddItem';
import SearchItem from './SearchItem';
import Content from './Content';
import Footer from './Footer';
import { useState, useEffect } from 'react';
import apiRequest from './apiRequest';


function App() {
  const API_URL = "http://localhost:8080/items";

  const [items, setItems] = useState([]);
  const [newItem, setNewItem] = useState('');
  const [search, setSearch] = useState('');
  const [fetchError, setFetchError] = useState(null);
  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Did not receve expected data");
        const listItems = await response.json();
        setItems(listItems);
        setFetchError(null);
      } catch (error) {
        setFetchError(error.message)
      } finally {
        setIsLoading(false);
      }
    }
    setTimeout(() => { fetchItems() }, 2000)
  }, [])


  const addItem = async (newItem) => {
    const id = items.length ? items[items.length - 1].id + 1 : 1;
    const myNewItem = { id, checked: false, item: newItem }
    const listItems = [...items, myNewItem];
    console.log(listItems);
    setItems(listItems)

    const postOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(myNewItem)
    }
    const result = await apiRequest(API_URL, postOptions);
    if (result) setFetchError(result)
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!newItem) return;
    addItem(newItem)
    setNewItem('')
  }

  const handleCheck = async (id) => {
    // Wierd Code , above my pay - grade 
    const listItems = items.map((item) => item.id === id ? { ...item, checked: !item.checked } : item)
    setItems(listItems)
    console.log(`Checked Value Updated : ${id}`);
    const updateOption = {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json'
      },
      body: null
    }
    const reqURL = `${API_URL}/${id}`;
    const result = await apiRequest(reqURL,updateOption);
    if (result) setFetchError(result);
  }

  const handleDelete = async (id) => {
    const listItems = items.filter((item) => item.id !== id);
    setItems(listItems)

    const deleteOperation = { method : 'DELETE'}
    const reqURL = `${API_URL}/${id}`;
    const result = await apiRequest(reqURL,deleteOperation);
    if (result) setFetchError(result);
  }

  return (
    <div className="App">
      <Header title="Groceries List" />
      <Additem newItem={newItem} setNewItem={setNewItem} handleSubmit={handleSubmit} />
      <SearchItem search={search} setSearch={setSearch} />
      <main>
        {isLoading && <p>Loading Items</p>}
        {!fetchError && !isLoading &&
          <Content items={items.filter(item => ((item.item).toLowerCase()).includes(search.toLowerCase()))}
            handleCheck={handleCheck}
            handleDelete={handleDelete} />
        }
      </main>
      <Footer length={items.length} />
    </div>
  );
}

export default App;
