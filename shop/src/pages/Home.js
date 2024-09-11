import profileImage from "../img/profile2.jpeg";
import Top3 from "../component/Top3";
import './Home-style.css';
export default function Home({ top3 }) {
    return(
        <>
            <div className="main">
                <div className="main-content">
                    <div className="main-content-left-box">
                        WELCOME,<br/>
                        YOUNG CHAN,<br/>
                        PORTFOLIO 👋️
                    </div>
                    <div className="main-content-right-box">
                        <img
                            src={profileImage}
                            alt="Profile"
                            className="circular-image"
                        />
                    </div>
                </div>
            </div>
            <div className="container">
                <div className="row">
                    {top3.map((item,i) => {
                        return (<Top3 data={top3[i]}/>)
                    })}
                </div>
            </div>
        </>
    )
}