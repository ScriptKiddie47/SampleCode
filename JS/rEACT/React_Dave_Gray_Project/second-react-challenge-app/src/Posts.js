const Posts = ({ URI, setReqTpe }) => {
    return (
        <div>
            <button onClick={() => setReqTpe('posts')}>
                Posts
            </button>
        </div>
    );
}
export default Posts;
