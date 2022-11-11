import { formData } from "./forms";

const form = document.querySelector("form")!;
form.addEventListener("submit", (e) => {
  e.preventDefault();
  const data = formData(form);
  console.log(data);
});

const something : any = null;
console.log(something.Hello)