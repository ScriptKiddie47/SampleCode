const Footer = ({ length }) => {
    return (
        <footer>
            <p>List Item{length > 0 ? "s = " + length : null}</p>
        </footer>
    )
}
export default Footer;