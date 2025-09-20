import React, { useState, useEffect } from "react";
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
    Tooltip,
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    IconButton,
} from "@chakra-ui/react";
import SweetCard from "./SweetCard";
import sweetApi from "../api/modules/sweet.api";
import { toast } from 'react-toastify'


const SweetDashboard = () => {
    // Placeholder sweets
    // const allSweets = [
    //     { id: 1, name: "Gulab Jamunlfvnslvnsdnvksnvfjnkskdgs", category: "Indian", price: 120, stockCount: 50 },
    //     { id: 2, name: "Ladoo", category: "Indian", price: 100, stockCount: 70 },
    //     { id: 3, name: "Brownie", category: "Western", price: 200, stockCount: 30 },
    //     { id: 4, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
    //     { id: 5, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
    //     { id: 6, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
    //     { id: 7, name: "Donut", category: "Bakery", price: 150, stockCount: 25 },
    // ];
    const [sweets, setSweets] = useState([]);
    const [categories, setCategories] = useState([]);
    const [filters, setFilters] = useState({
        name: "",
        category: "",
        minPrice: "",
        maxPrice: "",
    })

    useEffect(() => {
        const fetchData = async () => {
            const { res, err } = await sweetApi.getAll();
            if (res) {
                setSweets(res.sweets);
                setCategories(res.categories);
            }
            if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
        }
        fetchData();
    }, [])

    const handleChange = (event, field) => {
        setFilters(f => ({
            ...f,
            [field]: event.target.value
        }))
    }
    function cleanFilters(obj) {
        return Object.fromEntries(
            Object.entries(obj).filter(([_, v]) => v != null && v !== "" && v!=undefined)
        );
    }
    const applyFilters = async () => {
        const params = cleanFilters(filters)
        console.log(params)
        const { res, err } = await sweetApi.search(params);
        if (res) {
            setSweets(res);
        }
        if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
    };
    const checkFilters = () => {
        return (!filters.name && !filters.category && !filters.maxPrice && !filters.minPrice);
    }
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
                    <Input placeholder="Search by name" w="30%" onChange={(e) => handleChange(e, "name")} />
                    <Select placeholder="Category" w="20%" onChange={(e) => handleChange(e, "category")}>
                        {categories.map((category) => {
                            return (
                                <option value={category}>{category}</option>
                            )
                        })}
                    </Select>
                    <Input type="number" placeholder="Min Price" w="20%" onChange={(e) => handleChange(e, "minPrice")} />
                    <Input type="number" placeholder="Max Price" w="20%" onChange={(e) => handleChange(e, "maxPrice")} />
                    <Button colorScheme="teal" w="10%" onClick={applyFilters} isDisabled={checkFilters()}>Filter</Button>
                </HStack>
            </Box>

            <Box>
                <SimpleGrid columns={[1, 2, 3]} spacing={6}>
                    {sweets.map((sweet) => (
                        <SweetCard key={sweet.id} sweet={sweet} />
                    ))}
                </SimpleGrid>
            </Box>
        </VStack>
    );
};

export default SweetDashboard;
