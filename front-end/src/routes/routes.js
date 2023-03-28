import Home from "~/Pages/Home";
import FilmSchedule from "~/Pages/FilmSchedule";
import TheaterSchedule from "~/Pages/TheaterSchedule";
import TheaterSystem from "~/Pages/TheaterSystem";
import Promotion from "~/Pages/Promotion";
import config from "~/config";
import Event from "~/Pages/Event";
import Advertise from "~/Pages/Advertise";
import Recruitment from "~/Pages/Recruitment";
import Information from "~/Pages/Information";
import Account from "~/Pages/Account";
import Rules from "~/Pages/Rules";
import MemberRule from "~/Pages/MemberRule";
import TicketInstruction from "~/Pages/TicketInstruction";
import Policy from "~/Pages/Policy";
import UserSecurity from "~/Pages/UserSecurity";
import Register from "~/Pages/Register/Register";
import HomeLayout from "~/layouts/HomeLayout";
import PasswordForgot from "~/Pages/PasswordForgot";
import UnAuthorization from "~/Pages/UnAuthorization";
import UserList from "~/Pages/CRUDList/PageList/UserList";
import AddUser from "~/Pages/CRUDList/AddPages/AddUser";
import AddMovie from "~/Pages/CRUDList/AddPages/AddMovie";
import MovieList from "~/Pages/CRUDList/PageList/MovieList";
import Movie from "~/Pages/Movie";
import AddMovieSession from "~/Pages/CRUDList/AddPages/AddMovieSession";
import Booking from "~/Pages/Booking";
import ServiceBook from "~/Pages/ServiceBook";
import BookConfirm from "~/Pages/BookConfirm";
const publicRoutes = [
  {
    path: config.routes.Home,
    component: Home,
    layout: HomeLayout,
  },
  {
    path: config.routes.FilmSchedule,
    component: FilmSchedule,
    layout: HomeLayout,
  },
  {
    path: config.routes.TheaterSchedule,
    component: TheaterSchedule,
    layout: HomeLayout,
  },
  {
    path: config.routes.TheaterSystem,
    component: TheaterSystem,
    layout: HomeLayout,
  },
  {
    path: config.routes.Promotion,
    component: Promotion,
    layout: HomeLayout,
  },
  {
    path: config.routes.Event,
    component: Event,
    layout: HomeLayout,
  },
  {
    path: config.routes.Advertise,
    component: Advertise,
    layout: HomeLayout,
  },
  {
    path: config.routes.Recruitment,
    component: Recruitment,
    layout: HomeLayout,
  },
  {
    path: config.routes.Information,
    component: Information,
    layout: HomeLayout,
  },

  {
    path: config.routes.Rules,
    component: Rules,
    layout: HomeLayout,
  },
  {
    path: config.routes.MemberRule,
    component: MemberRule,
    layout: HomeLayout,
  },
  {
    path: config.routes.TicketInstruction,
    component: TicketInstruction,
    layout: HomeLayout,
  },
  {
    path: config.routes.Policy,
    component: Policy,
    layout: HomeLayout,
  },
  {
    path: config.routes.UserSecurity,
    component: UserSecurity,
    layout: HomeLayout,
  },
  {
    path: config.routes.Register,
    component: Register,
    layout: HomeLayout,
  },
  {
    path: config.routes.PasswordForgot,
    component: PasswordForgot,
    layout: HomeLayout,
  },
  {
    path: config.routes.UnAuthorization,
    component: UnAuthorization,
    layout: HomeLayout,
  },
  {
    path: config.routes.Movie,
    component: Movie,
    layout: HomeLayout,
  },
  {
    path: config.routes.Booking,
    component: Booking,
    layout: HomeLayout,
  },
  {
    path: config.routes.ServiceBook,
    component: ServiceBook,
    layout: HomeLayout,
  },
  {
    path: config.routes.BookConfirm,
    component: BookConfirm,
    layout: HomeLayout,
  },
];
export const privateRoutes = [
  {
    path: config.routes.Account,
    component: Account,
    layout: HomeLayout,
    role: "USER",
  },
  {
    path: config.routes.AdminHome,
    component: Account,
    layout: HomeLayout,
    role: "ADMIN",
  },
  {
    path: config.routes.ManagerHome,
    component: Account,
    layout: HomeLayout,
    role: "MANAGER",
  },
  {
    path: config.routes.UserList,
    component: UserList,
    layout: HomeLayout,
    role: "ADMIN",
  },
  {
    path: config.routes.AddUser,
    component: AddUser,
    layout: HomeLayout,
    role: "ADMIN",
  },
  {
    path: config.routes.AddMovie,
    component: AddMovie,
    layout: HomeLayout,
    role: "ADMIN",
  },
  {
    path: config.routes.MovieList,
    component: MovieList,
    layout: HomeLayout,
    role: "ADMIN",
  },
  {
    path: config.routes.AddMovieSession,
    component: AddMovieSession,
    layout: HomeLayout,
    role: "ADMIN",
  },
];
export default publicRoutes;
