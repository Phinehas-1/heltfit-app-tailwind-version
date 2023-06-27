/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: process.env. NODE_ENV ? 'jit' : undefined,
  purge: [" ./sIC/**/*.html", "./sIC/**/*. js"],
  content: [],
  theme: {
    extend: {},
  },
  plugins: [],
}

