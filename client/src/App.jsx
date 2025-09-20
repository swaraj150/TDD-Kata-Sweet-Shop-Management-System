import { useState } from 'react'

import './App.css'
import { ChakraProvider } from "@chakra-ui/react";
import { privateRoutes, publicRoutes } from './routes/Routes';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import NotFoundPage from './components/NotFoundPage';
import PrivateRoute from './routes/PrivateRoute';
function App() {
 
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
            {
              privateRoutes.map((route, index) => (
                  <Route path={route.path} key={index} element={
                    <PrivateRoute role={route.role}>
                      {route.element}
                    </PrivateRoute>
                  } />
              ))
            }
          <Route path="*" element={<NotFoundPage />} />
          </Routes>
          
        
        </BrowserRouter>
      </ChakraProvider>
    </>

    
  )
}

export default App
