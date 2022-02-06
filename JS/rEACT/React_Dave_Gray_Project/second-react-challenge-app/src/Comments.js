const Comments = ({ URI, setReqTpe }) => {
    return (
        <div>
            <button onClick={() => setReqTpe('comments')}>
                Comments
            </button>
        </div>
    );
}
export default Comments;
