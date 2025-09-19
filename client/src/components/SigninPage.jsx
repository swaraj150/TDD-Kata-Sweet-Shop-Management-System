import React from "react";
import { useForm } from "react-hook-form";

import { useNavigate } from 'react-router-dom'
import {
    Box,
    Button,
    FormControl,
    FormErrorMessage,
    FormLabel,
    Input,
    VStack,
    Heading,
} from "@chakra-ui/react";
import userApi from "../api/modules/user.api";
import { toast } from 'react-toastify'
const SignInPage=()=>{
    
  const navigate = useNavigate()
    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting },
    } = useForm();
    const onSubmit = async(values) => {
       const {res,err}=await userApi.signin(values);
       console.log(res)
       console.log(err)
       if (res && res.jwtToken && res.email) {
        localStorage.setItem('jwtoken', res.jwtoken)
        toast.success('Login successful! Welcome Back.')
        navigate('/home')
      }
      if (err) {
        localStorage.removeItem('jwtoken')
        toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
      }
    };

    return (
        <Box
            maxW="md"
            mx="auto"
            mt={10}
            p={6}
            borderWidth={1}
            borderRadius="lg"
            boxShadow="lg"
        >
            <Heading size="lg" textAlign="center" mb={6}>
                Sign In
            </Heading>
            <form onSubmit={handleSubmit(onSubmit)}>
                <VStack spacing={4}>
                    <FormControl isInvalid={errors.email}>
                        <FormLabel>Email</FormLabel>
                        <Input
                            type="email"
                            placeholder="Enter email"
                            {...register("email", { required: "Email is required" })}
                        />
                        <FormErrorMessage>{errors.email?.message}</FormErrorMessage>
                    </FormControl>

                    <FormControl isInvalid={errors.password}>
                        <FormLabel>Password</FormLabel>
                        <Input
                            type="password"
                            placeholder="Enter password"
                            {...register("password", { required: "Password is required" })}
                        />
                        <FormErrorMessage>{errors.password?.message}</FormErrorMessage>
                    </FormControl>

                    <Button
                        mt={4}
                        colorScheme="teal"
                        isLoading={isSubmitting}
                        type="submit"
                        w="full"
                    >
                        Sign In
                    </Button>
                </VStack>
            </form>
        </Box>
    );
}
export default SignInPage