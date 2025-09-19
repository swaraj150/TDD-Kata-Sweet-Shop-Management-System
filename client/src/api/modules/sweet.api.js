import privateClient from "../client/private.client";

const sweetEndpoints = {
    create: "sweets",
    update: (sweetId) => `sweets/${sweetId}`,
    delete: (sweetId) => `sweets/${sweetId}`,
    getAll: "sweets",
    search: "sweets/search"

};

const sweetApi = {
    create: async ({ name, category, price, stockCount }) => {
        try {
            const res = await privateClient.post(
                sweetEndpoints.create,
                { name, category, price, stockCount }
            )
            return { res };
        } catch (error) {
            return { error };

        }
    },
    update: async ({ id, name, category, price, stockCount }) => {
        try {
            const res = await privateClient.put(
                sweetEndpoints.update(id),
                { name, category, price, stockCount }
            )
            return { res };
        } catch (error) {
            return { error };

        }
    },

    delete: async ({ id }) => {
        try {
            const res = await privateClient.delete(
                sweetEndpoints.delete(id)
            )
            return { res }
        } catch (err) {
            return { err }
        }
    },
    getAll: async () => {
        try {
            const res = await privateClient.get(
                sweetEndpoints.getAll
            )
            return { res }
        } catch (err) {
            return { err }
        }
    },
    search: async ({ name, category, minPrice, maxPrice }) => {
        try {
            const res = await privateClient.get(
                sweetEndpoints.search, {
                params: {
                    name,
                    category,
                    minPrice,
                    maxPrice,
                },
            })
            return { res }
        } catch (err) {
            return { err }
        }
    }

}

export default sweetApi;