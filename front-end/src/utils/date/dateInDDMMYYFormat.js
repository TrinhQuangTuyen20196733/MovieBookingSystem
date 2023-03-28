const dateInDDMMYYFormat = (dateWithTime) => {
  return new Date(
    dateWithTime.getFullYear(),
    dateWithTime.getMonth(),
    dateWithTime.getDate()
  );
};
export default dateInDDMMYYFormat;
