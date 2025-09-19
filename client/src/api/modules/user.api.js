import publicClient from "../client/public.client";

const userEndpoints = {
  signin: 'auth/login',
  signup: 'auth/register',
}

const userApi = {
  signin:  async({ email, password }) => {

    try {
      const res = await publicClient.post(
        userEndpoints.signin,
        { email, password }
      )
      console.log(res);
      return {res}
    } catch (err) {
      return {err}
    }
  },
  signup: async ({ name, email, password, phoneNumber }) => {
    try {
      const res = await publicClient.post(
        userEndpoints.signup,
        { name, email, password, phoneNumber }
      )
      return {res}
    } catch (err) {
      return {err}
    }
  },
  
}

export default userApi