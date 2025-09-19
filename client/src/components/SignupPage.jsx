import React from "react";
import { useForm } from "react-hook-form";
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

const SignUpPage=()=>{
    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting },
    } = useForm();

    const onSubmit = async(values) => {
       const {res,err}=await userApi.signup(values);
       if (res && res.jwtoken && res.email) {
        localStorage.setItem('jwtoken', res.jwtoken)
        toast.success('Registration successful! Welcome aboard.')
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
                Sign Up
            </Heading>
            <form onSubmit={handleSubmit(onSubmit)}>
                <VStack spacing={4}>
                    <FormControl isInvalid={errors.name}>
                        <FormLabel>Name</FormLabel>
                        <Input
                            placeholder="Your Name"
                            {...register("name", { required: "Name is required" })}
                        />
                        <FormErrorMessage>{errors.name?.message}</FormErrorMessage>
                    </FormControl>

                    <FormControl isInvalid={errors.email}>
                        <FormLabel>Email</FormLabel>
                        <Input
                            type="email"
                            placeholder="Enter email"
                            {...register("email", {
                                required: "Email is required",
                                pattern: {
                                    value: /^\S+@\S+$/i,
                                    message: "Invalid email address",
                                },
                            })}
                        />
                        <FormErrorMessage>{errors.email?.message}</FormErrorMessage>
                    </FormControl>

                    <FormControl isInvalid={errors.password}>
                        <FormLabel>Password</FormLabel>
                        <Input
                            type="password"
                            placeholder="Enter password"
                            {...register("password", {
                                required: "Password is required",
                                minLength: { value: 6, message: "Minimum length is 6" },
                            })}
                        />
                        <FormErrorMessage>{errors.password?.message}</FormErrorMessage>
                    </FormControl>

                    <FormControl isInvalid={errors.phoneNumber}>
                        <FormLabel>Phone Number</FormLabel>
                        <Input
                            type="tel"
                            placeholder="1234567890"
                            {...register("phoneNumber", {
                                required: "Phone number is required",
                                pattern: {
                                    value: /^[0-9]{10}$/,
                                    message: "Enter a valid 10-digit phone number",
                                },
                            })}
                        />
                        <FormErrorMessage>{errors.phoneNumber?.message}</FormErrorMessage>
                    </FormControl>

                    <Button
                        mt={4}
                        colorScheme="teal"
                        isLoading={isSubmitting}
                        type="submit"
                        w="full"
                    >
                        Sign Up
                    </Button>
                </VStack>
            </form>
        </Box>
    );
}

export default SignUpPage