import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { ChakraProvider } from "@chakra-ui/react";
import SignUpPage from './components/SignupPage';
import { publicRoutes } from './routes/Routes';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
function App() {
  const [count, setCount] = useState(0)
  const handleSignIn = (data) => {
    console.log("Sign In:", data);
    // call backend login API here
  };

  const handleSignUp = (data) => {
    console.log("Sign Up:", data);
    // call backend register API here
  };
  return (
    <>
      <ToastContainer
        position='top-center'
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme='light'
        transition:Bounce
      />
      <ChakraProvider>
        
        <BrowserRouter>
          <Routes>
            {
            publicRoutes.map((route, index) => (
              <Route path={route.path} key={index} element={route.element} />
            ))
          }
          </Routes>
          
        
        </BrowserRouter>
      </ChakraProvider>
    </>

    
  )
}

export default App
