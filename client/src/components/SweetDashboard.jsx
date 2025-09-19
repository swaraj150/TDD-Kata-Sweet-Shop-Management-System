import React, { useState } from "react";
import {
    Box,
    Heading,
    SimpleGrid,
    Text,
    VStack,
    Input,
    Select,
    Button,
    HStack,
    Tooltip ,
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    IconButton,
} from "@chakra-ui/react";
import SweetCard from "./SweetCard";

const SweetDashboard = () => {
    // Placeholder sweets
    const allSweets = [
        { id: 1, name: "Gulab Jamunlfvnslvnsdnvksnvfjnkskdgs", category: "Indian", price: 120, stockCount: 50 },
        { id: 2, name: "Ladoo", category: "Indian", price: 100, stockCount: 70 },
        { id: 3, name: "Brownie", category: "Western", price: 200, stockCount: 30 },
        { id: 4, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
        { id: 5, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
        { id: 6, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
        { id: 7, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
    ];

    const [sweets, setSweets] = useState(allSweets);
    const [search, setSearch] = useState("");
    const [category, setCategory] = useState("");
    const [minPrice, setMinPrice] = useState("");
    const [maxPrice, setMaxPrice] = useState("");

    // Filter sweets locally
    const applyFilters = () => {
        let filtered = allSweets;

        if (search) {
            filtered = filtered.filter((s) =>
                s.name.toLowerCase().includes(search.toLowerCase())
            );
        }

        if (category) {
            filtered = filtered.filter((s) => s.category === category);
        }

        if (minPrice) {
            filtered = filtered.filter((s) => s.price >= parseFloat(minPrice));
        }

        if (maxPrice) {
            filtered = filtered.filter((s) => s.price <= parseFloat(maxPrice));
        }

        setSweets(filtered);
    };
    return (
        <VStack spacing={6} align="stretch" p={6}>
            <Heading mb={6} textAlign="center">
                Sweet Dashboard
            </Heading>
            <Box
                borderWidth="1px"
                borderRadius="lg"
                p={4}
                boxShadow="md"
                bg="gray.50"
            >
                <HStack spacing={2} align="flex-end">
                    <Input placeholder="Search by name" w="30%" />
                    <Select placeholder="Category" w="20%">
                        <option value="Indian">Indian</option>
                        <option value="Bengali">Bengali</option>
                    </Select>
                    <Input type="number" placeholder="Min Price" w="20%" />
                    <Input type="number" placeholder="Max Price" w="20%" />
                    <Button colorScheme="teal" w="10%">Filter</Button>
                </HStack>
            </Box>

            <Box>
                <SimpleGrid columns={[1, 2, 3]} spacing={6}>
                    {sweets.map((sweet) => (
                        // <Box
                        //     key={sweet.id}
                        //     borderWidth="1px"
                        //     borderRadius="lg"
                        //     p={4}
                        //     boxShadow="md"
                        //     maxW="full"
                        //     height="140px"
                        //     display="flex"
                        // >
                        //     <VStack
                        //         spacing={2}
                        //         align="start"
                        //         justify="space-between"
                        //         w="full"
                        //         h="full"
                        //     >
                        //         <Tooltip label={sweet.name} hasArrow>
                        //             <Text
                        //                 fontWeight="bold"
                        //                 fontSize="lg"
                        //                 isTruncated
                        //                 maxW="200px"
                        //                 textAlign="center"
                        //                 mx="auto"
                        //             >
                        //                 {sweet.name}
                        //             </Text>
                        //         </Tooltip>
                        //         <Tooltip label={sweet.category} hasArrow>
                        //             <Text
                        //                 color="gray.600"
                        //                 fontSize="sm"
                        //                 isTruncated
                        //                 maxW="200px"
                        //                 textAlign="center"
                        //                 mx="auto"
                        //             >
                        //                 Category: {sweet.category}
                        //             </Text>
                        //         </Tooltip>
                            
                        //         <HStack w="full" justify="space-between" align="center">
                        //             <Text fontWeight="semibold" color="teal.600">
                        //                 ₹{sweet.price}
                        //             </Text>
                        //             <Text fontWeight="semibold">
                        //                 Stock: {sweet.stockCount}
                        //             </Text>
                        //             <Button size="sm" colorScheme="teal">
                        //                 Buy
                        //             </Button>
                        //         </HStack>
                        //     </VStack>
                        // </Box>
                        <SweetCard sweet={sweet}/>
                    ))}
                </SimpleGrid>
            </Box>
        </VStack>
    );
};

export default SweetDashboard;
