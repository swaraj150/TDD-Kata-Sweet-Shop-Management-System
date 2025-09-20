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
import { useUser } from "../context/UserContext";
import Header from "./Header";
import { useNavigate } from "react-router-dom";


const SweetDashboard = () => {
    const navigate = useNavigate();
    const [sweets, setSweets] = useState([]);
    const [categories, setCategories] = useState([]);
    const { user, setUser } = useUser();
    const [isAdmin, setIsAdmin] = useState(false);
    const defaultFilter = {
        name: "",
        category: "",
        minPrice: "",
        maxPrice: "",

    }
    const [filters, setFilters] = useState(defaultFilter);
    const [fetch, setFetch] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            const { res, err } = await sweetApi.getAll();
            if (res) {
                setSweets(res.sweets);
                setCategories(res.categories);
            }
            if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
        }
        if (fetch) {
            fetchData();
            setFetch(false);
        }
    }, [fetch])

    useEffect(() => {
        if (user) {
            setIsAdmin(user.role == "ADMIN");
        }
        else {
            setIsAdmin(false);
        }
    }, [user])

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

    const clearFilters = () => {
        if (!fetch) setFetch(true);
        setFilters(defaultFilter);
    }
    const checkFilters = () => {
        return (filters.name == "" && filters.category == "" && filters.maxPrice == "" && filters.minPrice == "");
    }

    const applyFilters = async () => {
        const params = cleanFilters(filters)
        const { res, err } = await sweetApi.search(params);
        if (res) {
            setSweets(res);
        }
        if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
    };




    const handleOnBuyClick = async (id, quantity, factor = -1) => {
        if (factor == -1) {
            const { res, err } = await sweetApi.purchase({ id: id, stock: quantity * (factor) });
            if (res) {
                setSweets((prevSweets) =>
                    prevSweets.map((sweet) => (sweet.id === id ? res : sweet))
                );
                toast.success(`Sweet Purchased!`);
            }
            if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')

        }
        else {
            const { res, err } = await sweetApi.restock({ id: id, stock: quantity * (factor) });
            if (res) {
                setSweets((prevSweets) =>
                    prevSweets.map((sweet) => (sweet.id === id ? res : sweet))
                );
                toast.success(`Sweet Restock!`);
            }
            if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')

        }

    }
    const handleOnUpdateClick = async (sweet) => {
        navigate("/sweet-form", { state: { sweet } });
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
            <Header />
            <Box
                borderWidth="1px"
                borderRadius="lg"
                p={4}
                boxShadow="md"
                bg="gray.50"
            >
                <HStack spacing={2} align="flex-end">
                    <Input
                        placeholder="Search by name"
                        w="30%"
                        value={filters.name}
                        onChange={(e) => handleChange(e, "name")}
                    />
                    <Select
                        placeholder="Category"
                        w="20%"
                        value={filters.category}
                        onChange={(e) => handleChange(e, "category")}
                    >
                        {categories.map((category) => (
                            <option key={category} value={category}>
                                {category}
                            </option>
                        ))}
                    </Select>
                    <Input
                        type="number"
                        placeholder="Min Price"
                        w="20%"
                        value={filters.minPrice}
                        onChange={(e) => handleChange(e, "minPrice")}
                    />
                    <Input
                        type="number"
                        placeholder="Max Price"
                        w="20%"
                        value={filters.maxPrice}
                        onChange={(e) => handleChange(e, "maxPrice")}
                    />
                    <Button
                        colorScheme="teal"
                        w="10%"
                        onClick={applyFilters}
                        isDisabled={checkFilters()}
                    >
                        Filter
                    </Button>
                    <Button
                        colorScheme="teal"
                        w="10%"
                        onClick={clearFilters} >
                        Reset
                    </Button>
                </HStack>
            </Box>

            <Box>
                {sweets.length === 0 ? (
                    <Box textAlign="center" py={10} color="gray.500" fontWeight="medium">
                        No sweets to display 🍬
                    </Box>
                ) : (
                    <SimpleGrid columns={[1, 2, 3]} spacing={6}>
                        {sweets.map((sweet) => (
                            <SweetCard
                                key={sweet.id}
                                sweet={sweet}
                                onBuyClick={handleOnBuyClick}
                                onUpdateClick={handleOnUpdateClick}
                                onDeleteClick={handleOnDeleteClick}
                                isAdmin={isAdmin}
                            />
                        ))}
                    </SimpleGrid>
                )}

            </Box>
        </VStack>
    );
};

export default SweetDashboard;
