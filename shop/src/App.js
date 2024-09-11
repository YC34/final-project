import './App.css';
import Header from "./pages/common/Header";
import top3Mock from "./data/top3Mock";
import {Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import LogIn from "./pages/auth/LogIn";
import Skill from "./pages/skill/Skill";
import {BoardAPI} from "./apis/BoardAPI";


// component App
function App() {


  // data
  const boardData = BoardAPI;


  // render App
  return (
      <div className="App">
          <Header/>
          <Routes>
              <Route path="/" element={<Home top3={top3Mock} />} />
              <Route path="/login" element={<LogIn/>} />
              <Route path="/skill" element={<Skill/>} />
          </Routes>
      </div>
  );
}


export default App;
