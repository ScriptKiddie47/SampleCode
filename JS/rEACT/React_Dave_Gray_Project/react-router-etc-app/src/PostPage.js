import { useParams, Link } from 'react-router-dom'

const PostPage = ({ posts, handleDelete }) => {
    const { id } = useParams();
    const post = posts.find(post => (post.id).toString() === id);
    return (
        <main className='PostPage'>
            <article className='post'>
                {post &&
                    <>
                        <h2>{post.title}</h2>
                        <p className='postDate'>{post.datetime}</p>
                        <p className='postDate'>{post.body}</p>
                        <button onClick={(e) => handleDelete(id)}>Delete Post</button>
                    </>
                }
                {!post &&
                    <>
                        <h2>Page Not Found</h2>
                        <p>Well , thats a disappointment</p>
                        <p><Link to="/">Visit our homepage</Link></p>
                    </>
                }
            </article>
        </main>
    );
}
export default PostPage;
