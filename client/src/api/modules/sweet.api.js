import privateClient from "../client/private.client";

const sweetEndpoints = {
    create: "sweets",
    update: (sweetId) => `sweets/${sweetId}`,
    delete: (sweetId) => `sweets/${sweetId}`,
    getAll: "sweets",
    search: "sweets/search",
    purchase:(sweetId)=>`sweets/${sweetId}/purchase`,
    restock:(sweetId)=>`sweets/${sweetId}/restock`,

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
    search: async (params) => {
        try {
            const res = await privateClient.get(
                sweetEndpoints.search, {
                    params:params
                })
                return { res }
            } catch (err) {
                return { err }
            }
    },
        
    purchase: async ({id,stock}) => {
        try {
            
            const res = await privateClient.post(
                sweetEndpoints.purchase(id),
                {stock}
            )
            return { res };
        } catch (error) {
            return { error };

        }
    },
    restock: async ({id,stock}) => {
        try {
            
            const res = await privateClient.post(
                sweetEndpoints.restock(id),
                {stock}
            )
            return { res };
        } catch (error) {
            return { error };

        }
    },
}

export default sweetApi;