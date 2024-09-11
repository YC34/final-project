import React, { useState } from 'react';
import {UserInput} from "../../component/Input";
import './login-style.css';
import Button from 'react-bootstrap/Button';
import {useNavigate} from "react-router-dom";

// component login
export default function LogIn( ) {


    // state : email
    const [email, setEmail] = useState('');
    // state : password
    const [password, setPassword] = useState('');

    // state: login 상태.
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    // state : error message
    const [errorMessage, setErrorMessage] = useState('');


    const navigate = useNavigate();

    // event handler : email change handler
    const onChangeEmail = (event) => {
        setEmail(event.target.value);
    }
    // event handler : password change handler
    const onChangePassword = (event) => {
        setPassword(event.target.value);
    }
    // event handler : login button
    const onClickLoginButton =  () => {
        fetch()
    };



    // render login componet
    return (
        <div className='login-container'>
            <div className='login-title'> LOGIN</div>
            <div className='login-logo'></div>
            <UserInput type='text' name='email' placeholder='Email을 입력하세요.' value={email} onChange={onChangeEmail}/>
            <UserInput type='password' name='password' placeholder='비밀번호를 입력하세요.' value={password} onChange={onChangePassword}/>
            <Button as="input" type="submit" value="Submit" onClick={onClickLoginButton}/>{' '}
        </div>
    );
}
