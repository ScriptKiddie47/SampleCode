const WindowColor = ({ myColor,handleColorChange}) => {
    return (
        <div>
            <input autoFocus id="changeWindowColor" type="text"
                value={myColor}
                onChange={(e) => handleColorChange(e.target.value)}
            />
        </div>
    );
}
export default WindowColor;
