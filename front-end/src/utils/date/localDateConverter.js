const localDateConverter = (utcDateString) => {
  // create a new date object from the UTC string
  const utcDate = new Date(utcDateString);

  // get the UTC timestamp
  const utcTimestamp = utcDate.getTime();

  // get the local timezone offset in milliseconds
  const timezoneOffset = new Date().getTimezoneOffset() * 60 * 1000;

  // calculate the local timestamp
  const localTimestamp = utcTimestamp - timezoneOffset;

  // create a new date object from the local timestamp
  const localDate = new Date(localTimestamp);

  // return the local date string
  return localDate;
};
export default localDateConverter;
