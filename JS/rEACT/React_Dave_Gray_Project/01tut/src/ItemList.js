import LineItem from "./LineItem";

const ItemList = ({ items, handleCheck, handleDelete }) => {
    return (
        <ul>
            {items.map((item) => (
                <LineItem
                    key={item.id} // Unique
                    item={item}
                    handleCheck={handleCheck}
                    handleDelete={handleDelete} />
            ))}
        </ul>
    )
}
export default ItemList;