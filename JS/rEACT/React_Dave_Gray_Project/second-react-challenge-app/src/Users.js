const Users = ({ URI, setReqTpe }) => {
    return (
        <div>
            <button onClick={() => setReqTpe('users')}>
                Users
            </button>
        </div>
    );
}
export default Users;
