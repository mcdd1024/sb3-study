import {defineConfig} from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
    base: "/sb3-study/",
    description: "A template for docs create by cbq1024",
    title: 'Sb3-Study',
    markdown: {
        math: true,
        lineNumbers: true
    },

    themeConfig: {
        head: [["link", {rel: "icon", href: "/docs-template/mcdd.jpg"}]],
        outline: [1, 4],
        lastUpdated: true,

        // https://vitepress.dev/reference/default-theme-config
        nav: [
            {text: 'Home', link: '/'},
            {
                text: '🍃 Spring Boot',
                items: [
                    {text: '🤪 开篇', link: '/spring/spring01.md'},
                    {text: '🏀 夯实 IOC 容器', link: '/spring/spring02.md'},
                    {text: '🦆 夯实 AOP 编程', link: '/spring/spring03.md'},
                    {text: '🐔 Web 开发', link: '/spring/spring04.md'},
                    {text: '🐲 整合日志', link: '/spring/spring05.md'},
                    {text: '❤️ Spring Boot 服务监控', link: '/spring/spring06.md'},
                    {text: '🤬 集成 MybatisPlus', link: '/spring/spring07.md'},
                    {text: '🤯 集成 Redis', link: '/spring/spring08.md'},
                    {text: '🤠 集成 POI', link: '/spring/spring09.md'},
                    {text: '🥸 Index ', link: '/spring/index.md'},
                ]
            },
            {
                text: "工具 🔨",
                items: [
                    {text: '💻 环境安装', link: '/tools/path-install.md'},
                ]
            },
            {
                text: "版本信息 😶‍🌫️",
                link: "/CHANGELOG",
            },
        ],

        sidebar: [
            {
                text: '🍃 Spring Boot',
                link: '/spring/index.md',
                collapsed: true,
                items: [
                    {text: '🤪 开篇', link: '/spring/spring01.md'},
                    {text: '🏀 夯实 IOC 容器', link: '/spring/spring02.md'},
                    {text: '🦆 夯实 AOP 编程', link: '/spring/spring03.md'},
                    {text: '🐔 Web 开发', link: '/spring/spring04.md'},
                    {text: '🐲 整合日志', link: '/spring/spring05.md'},
                    {text: '❤️ Spring Boot 服务监控', link: '/spring/spring06.md'},
                    {text: '🤬 集成 MybatisPlus', link: '/spring/spring07.md'},
                    {text: '🤯 集成 Redis', link: '/spring/spring08.md'},
                    {text: '🤠 集成 POI', link: '/spring/spring09.md'},
                ]
            },
            {
                text: "工具 🔨",
                link: '/tools/index.md',
                collapsed: false,
                items: [
                    {text: '💻 环境安装', link: '/tools/path-install.md'},
                ]
            },
            {
                text: "版本信息 😶‍🌫️",
                link: "/CHANGELOG",
            },
        ],
        footer: {
            copyright: "Copyright © 2024-present mcdd1024",
        },
        search: {
            provider: "local",
            options: {
                translations: {
                    button: {
                        buttonText: "搜索文档",
                        buttonAriaLabel: "搜索文档",
                    },
                    modal: {
                        noResultsText: "无法找到相关结果",
                        resetButtonTitle: "清除查询条件",
                        footer: {
                            selectText: "选择",
                            navigateText: "切换",
                        },
                    },
                },
            },
        },
        socialLinks: [
            {icon: "github", link: "https://github.com/mcdd1024/sb3-study"},
            {
                icon: {
                    svg: '<svg t="1713342337553" class="icon" viewBox="0 0 1129 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2496" width="16" height="16"><path d="M234.909 9.656a80.468 80.468 0 0 1 68.398 0 167.374 167.374 0 0 1 41.843 30.578l160.937 140.82h115.07l160.936-140.82a168.983 168.983 0 0 1 41.843-30.578A80.468 80.468 0 0 1 930.96 76.445a80.468 80.468 0 0 1-17.703 53.914 449.818 449.818 0 0 1-35.406 32.187 232.553 232.553 0 0 1-22.531 18.508h100.585a170.593 170.593 0 0 1 118.289 53.109 171.397 171.397 0 0 1 53.914 118.288v462.693a325.897 325.897 0 0 1-4.024 70.007 178.64 178.64 0 0 1-80.468 112.656 173.007 173.007 0 0 1-92.539 25.75h-738.7a341.186 341.186 0 0 1-72.421-4.024A177.835 177.835 0 0 1 28.91 939.065a172.202 172.202 0 0 1-27.36-92.539V388.662a360.498 360.498 0 0 1 0-66.789A177.03 177.03 0 0 1 162.487 178.64h105.414c-16.899-12.07-31.383-26.555-46.672-39.43a80.468 80.468 0 0 1-25.75-65.984 80.468 80.468 0 0 1 39.43-63.57M216.4 321.873a80.468 80.468 0 0 0-63.57 57.937 108.632 108.632 0 0 0 0 30.578v380.615a80.468 80.468 0 0 0 55.523 80.469 106.218 106.218 0 0 0 34.601 5.632h654.208a80.468 80.468 0 0 0 76.444-47.476 112.656 112.656 0 0 0 8.047-53.109v-354.06a135.187 135.187 0 0 0 0-38.625 80.468 80.468 0 0 0-52.304-54.719 129.554 129.554 0 0 0-49.89-7.242H254.22a268.764 268.764 0 0 0-37.82 0z m0 0" fill="#20B0E3" p-id="2497"></path><path d="M348.369 447.404a80.468 80.468 0 0 1 55.523 18.507 80.468 80.468 0 0 1 28.164 59.547v80.468a80.468 80.468 0 0 1-16.094 51.5 80.468 80.468 0 0 1-131.968-9.656 104.609 104.609 0 0 1-10.46-54.719v-80.468a80.468 80.468 0 0 1 70.007-67.593z m416.02 0a80.468 80.468 0 0 1 86.102 75.64v80.468a94.148 94.148 0 0 1-12.07 53.11 80.468 80.468 0 0 1-132.773 0 95.757 95.757 0 0 1-12.875-57.133V519.02a80.468 80.468 0 0 1 70.007-70.812z m0 0" fill="#20B0E3" p-id="2498"></path></svg>',
                },
                link: "https://space.bilibili.com/1045499440?spm_id_from=333.1007.0.0",
            },
        ],
    }
})
