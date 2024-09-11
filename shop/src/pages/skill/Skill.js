import { Card, Col, Row } from "react-bootstrap";
import './Skill-style.css';

export default function Skill() {
    return (
        <div className="card-container"> {/* 수정된 클래스명 사용 */}
            <Row xs={1} md={3} className="g-4">
                <Col>
                    <Card>
                        <Card.Header>📁 Back End</Card.Header>
                        <Card.Img variant="top" src="assets/card-1.png" />
                        <Card.Body>
                            <Card.Title>Back End Skill</Card.Title>
                            <Card.Text>
                                JAVA(Version : 17 ) - 백엔드에 기초가 되는 언어<br/>
                                SPRING BOOT(Version : 3.3.2) - 백엔드 프레임워크<br/>
                                SPRING SECURITY - 인증과 인가에 필요한 프레임워크<br/>
                                JWT(Json Web Tokens) - 보안을 위한 도구<br/>
                                MYBATIS - DB와 연동을 위한 프레임워크<br/>
                                <br/><br/>
                                &nbsp;▶️ dto의 사용으로 DB와 대응되는 entity를 노출시키지 않았습니다.
                                <br/>
                                &nbsp;▶️ controller,service,dao단을 구분하여 해당 레이어에 맞는 역할만 부여.
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>


                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Header>📈 Data </Card.Header>
                        <Card.Img variant="top" src="holder.js/100px160" />
                        <Card.Body>
                            <Card.Title> 데이터 수집 및 정제</Card.Title>
                            <Card.Text>
                                This is a longer card with supporting text below as a natural
                                lead-in to additional content. This content is a little bit
                                longer.
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Header>🏞️ Front & ENV</Card.Header>
                        <Card.Img variant="top" src="holder.js/100px160" />
                        <Card.Body>
                            <Card.Title>프론트 및 개발 환경</Card.Title>
                            <Card.Text>
                                This is a longer card with supporting text below as a natural
                                lead-in to additional content. This content is a little bit
                                longer.
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </div>
    );
}
