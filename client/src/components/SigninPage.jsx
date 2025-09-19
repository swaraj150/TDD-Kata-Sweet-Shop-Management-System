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

const SignInPage=()=>{
    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting },
    } = useForm();
    const onSubmit = () => { };

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