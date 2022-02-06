import Row from "./Row";

const ShowData = ({ items }) => {
    return (
        <div>
            <p>Number of Objects : {Object.keys(items[0]).length} </p>
            <p>Objects Keys : {Object.keys(items[0])}</p>
            <table>
                <thead>
                    <tr>
                        {Object.keys(items[0]).map(ele =>
                            <th key={ele}>{ele}</th>
                        )}
                    </tr>
                </thead>
                <tbody>
                    {items.map(ele =>
                        <tr key={ele.id}>
                            {Object.entries(ele).map(([key,value]) =>
                                <td key={key}>{JSON.stringify(value)}</td>
                            )}
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}
export default ShowData;
