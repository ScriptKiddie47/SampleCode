import { useState } from 'react';
import WindowColor from './WindowColor';

function App() {

  const [myColor, setmyColor] = useState('')
  const whataboutmycolor = 'red'

  const handleColorChange = (newColor) => {
    console.log(` Color : ${myColor}`)
    console.log(` New Color : ${newColor}`)
    setmyColor(newColor);
  }

  const divStyle = {
    backgroundColor:  `${myColor}`
  }

  return (
    <div style={divStyle}>
      <p>My React app</p>
      <WindowColor myColor={myColor} handleColorChange={handleColorChange} />
      
    </div>

  );
}

export default App;
