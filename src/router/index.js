import Vue from 'vue'
import Router from 'vue-router'

import IndexComponent from '@/components/IndexComponent.vue'
import ProjectApp from '@/ProjectApp.vue'
import Blog from '@/Blog.vue'
import ProjectDetails from '@/projectDetailsApp.vue'
import BlogDetails from '@/BlogDetailsApp.vue'
import NotFoundComponent from '@/components/NotFoundComponent.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/home',
            name: 'home',
            component: IndexComponent
        },
        {
            path: '/project',
            name: 'project',
            component: ProjectApp
        },
        {
            path: '/project/:page',
            name: 'project',
            component: ProjectApp
        },
        {
            path: '/blog',
            name: 'blog',
            component: Blog
        },
        {
            path: '/blog/:page',
            name: 'blog',
            component: Blog
        },
        {
            path: '/projectDetails',
            name: 'projectDetails',
            component: ProjectDetails
        },
        {
            path: '/blogDetails',
            name: 'blogDetails',
            component: BlogDetails
        },
        {
            path: '*',
            component: NotFoundComponent
        },
    ]
})