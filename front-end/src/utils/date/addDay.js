const addDay = (date, amount) => {
  const date_copy = new Date(date);
  date_copy.setDate(date.getDate() + amount);
  return date_copy;
};
export default addDay;
