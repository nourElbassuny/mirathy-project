import { Routes } from '@angular/router';

import {HomeComponent} from './component/home/home';

import { About } from './component/about/about';
// import { Contact } from './component/contact/contact';
import {ContactComponent} from './component/contact/contact';

import { Login } from './component/login/login';
import { Register } from './component/register/register';
import { HistoryComponent } from './component/history/history';
// import { Dashboard } from './component/dashboard/dashboard';
import { authGuard } from './guards/auth.guard';
import { NotFoundPage } from './component/not-found-page/not-found-page';

export const routes: Routes = [
  { path: '', redirectTo: '/about', pathMatch: 'full' },
  { path: 'home', component: HomeComponent,title:"home"   },
  { path: 'about', component: About,title:"about"  },
  { path: 'contact', component: ContactComponent ,title:"contact"  },
  { path: 'login', component: Login ,title:"login"  },
  { path: 'register', component: Register,title:"register"  },
  { path: 'history', component: HistoryComponent, canActivate: [authGuard], title:"history"  },
  // { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: '**', component: NotFoundPage, title:"not-found"  },
];
