import React, { useState, useEffect } from "react";
import {
    Box,
    Heading,
    SimpleGrid,
    VStack,
    Input,
    Select,
    Button,
    HStack,
    
} from "@chakra-ui/react";
import SweetCard from "./SweetCard";
import sweetApi from "../api/modules/sweet.api";
import { toast } from 'react-toastify'


const SweetDashboard = () => {
    
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


    const cleanFilters = (obj) => {
        return Object.fromEntries(
            Object.entries(obj).filter(([_, v]) => v != null && v !== "" && v != undefined)
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


    const handleOnBuyClick = async (id) => {
        const { res, err } = await sweetApi.purchase({ id: id, stock: -1 });
        if (res) {
            setSweets((prevSweets) =>
                prevSweets.map((sweet) => (sweet.id === id ? res : sweet))
            );
            toast.success("Sweet purchased!");
        }
        if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')

    }
    const handleOnUpdateClick = async (id) => {
        // Navigate("/")
    }

    const handleOnDeleteClick = async (id) => {
        const { res, err } = await sweetApi.delete({ id: id });
        if (res) {
            setSweets((prevSweets) => prevSweets.filter((sweet) => sweet.id !== id));
            toast.success("Sweet Deleted!");
        }
        if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
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
                        <SweetCard key={sweet.id} sweet={sweet} onBuyClick={handleOnBuyClick} onUpdateClick={handleOnUpdateClick} onDeleteClick={handleOnDeleteClick} showMenu={true}/>
                    ))}
                </SimpleGrid>
            </Box>
        </VStack>
    );
};

export default SweetDashboard;
