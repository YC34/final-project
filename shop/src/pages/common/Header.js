import {Container, Nav, Navbar ,Button} from "react-bootstrap";
import {useNavigate} from "react-router-dom";

export default function Header(){


    const navigate = useNavigate();

    return(
        <>
            <Navbar bg="light" data-bs-theme="light">
                <Container>
                    <Navbar.Brand onClick={ () => {navigate('/')}} style={{fontWeight:'bolder' ,fontSize:"xx-large"}}>👨‍💻</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => {navigate('/skill')}}>SKILL</Nav.Link>
                        <Nav.Link onClick={ () => { navigate('/api_docs')}}>API_DOCS</Nav.Link>
                        <Nav.Link onClick={ () => {navigate('/board')}} >BOARD</Nav.Link>
                        <Nav.Link onClick={ () => {navigate('/todo')}}>TODO</Nav.Link>
                        <Nav.Link onClick={ () => { navigate('/next_step')}}>NEXT_STEP</Nav.Link>
                    </Nav>
                    <Button variant="outline-info" style={{ marginRight:'5px' , fontWeight:'bolder'}} onClick={()=>{navigate("/login")} }>LOGIN</Button>
                    <Button variant="outline-primary" style={{ fontWeight:'bolder'}} onClick={()=>{navigate('/sign_up')}}>SIGNUP</Button>
                </Container>
            </Navbar>
        </>
    )
}