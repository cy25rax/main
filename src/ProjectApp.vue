<template>
    <div>
        <HeaderComponent />

        <div class="banner page">
            <div class="banner_intro">
                <h1 class="banner_intro_title">
                    Articles & News
                </h1>
                <p class="banner_intro_text">
                    Home / Blog
                </p>
            </div>
        </div>

        <div class="projects page">
            <div class="projects_categories">
                <input type="checkbox" name="*" id="Bathroom">
                <label @click="click('Bathroom', $event)" class="projects_categories_text" for="Bathroom">Bathroom</label>

                <input type="checkbox" name="*" id="Bed Room">
                <label @click="click('Bed Room')" class="projects_categories_text" for="Bed Room">Bed Room</label>

                <input type="checkbox" name="*" id="Kitchen">
                <label @click="click('Kitchen')" class="projects_categories_text" for="Kitchen">Kitchen</label>

                <input type="checkbox" name="*" id="Living Area">
                <label @click="click('Living Area')" class="projects_categories_text" for="Living Area">Living Area</label>
            </div>

            <div class="projects_list">
                <ProjectComponent v-for="(item) in getProjects" :key="item.id" :imageUrl="item.img" :title="item.title"
                    :label="item.label" :like="item.like" @like-change="updateLike(item.id, $event)">
                </ProjectComponent>
            </div>

            <div class="projects_pagination">
                <a href="#" class="projects_pagination_page">01</a>
                <a href="#" class="projects_pagination_page">02</a>
                <a href="#" class="projects_pagination_page">03</a>
                <a href="#" class="projects_pagination_page"><img src="img/Vector.png" alt=""></a>
            </div>
        </div>

        <FooterComponent />
    </div>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';
import FooterComponent from './components/FooterComponent.vue';
import ProjectComponent from './components/ProjectComponent.vue';

export default {
    name: "ProjectApp",
    data() {
        return {
            filter: [],
            projects: [
                { id: 0, img: 'Bedroomtable.jpg', title: 'Classic Minimal Bedroom', label: 'Bathroom', like: false },
                { id: 1, img: 'table.jpg', title: 'Classic Minimal Bedroom', label: 'Bathroom', like: false },
                { id: 2, img: 'Bedroomtable.jpg', title: 'Classic Minimal Bedroom', label: 'Bed Room', like: false },
                { id: 3, img: 'table.jpg', title: 'Classic Minimal Bedroom', label: 'Kitchen', like: false },
                { id: 4, img: 'Bedroomtable.jpg', title: 'Classic Minimal Bedroom', label: 'Living Area', like: false },
            ]
        };
    },
    computed: {
        getProjects() {
            if (this.filter.length === 0) {
                return this.projects
            } else {
                const tempProjects = [];
                this.filter.forEach(element => {
                    this.projects.forEach(el => {
                        if (el.label === element) {
                            tempProjects.push(el);
                        }
                    });
                });
                return tempProjects
            }
        }
    },
    methods: {
        click(filter) {
            for (let index = 0; index < this.filter.length; index++) {

                if (this.filter[index] === filter) {
                    this.filter.splice(index, 1)
                    return
                }
            }
            this.filter.push(filter)
        },
        updateLike(index, like) {
            this.projects[index].like = like
        }
    },
    components: { HeaderComponent, FooterComponent, ProjectComponent }
};
</script>

<style lang="scss" scoped>
* {
    margin: 0;
    padding: 0;
    font-family: 'DM Serif Display', serif;
}

a {
    text-decoration: none;
}

img {
    max-width: 100%;
    max-height: 100%;
}

.page {
    padding-left: calc(50% - 1200px / 2);
    padding-right: calc(50% - 1200px / 2);
}

.banner {
    background-image: url("../public/img/banner.jpg");
    background-size: no-repeat;
    background-position: center;
    background-size: cover;
    height: 356px;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    align-items: center;

    &_intro {
        max-width: 503px;
        background-color: #FFFFFF;
        border-radius: 37px 37px 0px 0px;
        display: flex;
        flex-direction: column;
        align-items: center;

        &_title {
            font-style: normal;
            font-weight: 400;
            font-size: 50px;
            line-height: 125%;
            color: #292F36;
            padding-top: 47px;
            padding-left: 78px;
            padding-right: 78px;
        }

        &_text {
            font-family: 'Jost';
            font-style: normal;
            font-weight: 400;
            font-size: 22px;
            line-height: 150%;
            letter-spacing: 0.01em;
            color: #4D5053;
            padding-bottom: 41px;
        }
    }
}

.projects {
    padding-top: 200px;
    padding-bottom: 100px;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 61px;

    &_categories {
        display: inline-block;
        justify-content: center;
        align-items: center;
        gap: 30px;

        padding-top: 26px;
        padding-bottom: 26px;

        border: #CDA274 solid 2px;
        border-radius: 18px;

        &_text {
            color: #292F36;
            text-align: center;
            font-size: 18px;
            font-family: Jost;
            font-style: normal;
            font-weight: 600;
            line-height: 125%;
            letter-spacing: 0.36px;

            padding: 26px 66px;

        }
    }

    &_list {
        columns: 2;
    }

    &_pagination {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 20px;

        &_page {
            width: 52px;
            height: 52px;
            border: 1px solid #CDA274;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Jost';
            font-style: normal;
            font-weight: 500;
            font-size: 16px;
            line-height: 150%;
            text-transform: capitalize;
            color: #292F36;
        }

        & :first-child {
            background: #F4F0EC;
            border: none;
        }
    }
}

input[type=checkbox]+label {
    background: #FFFFFF;
    padding: 16px 66px;
    border-radius: 18px;
}

input[type=checkbox]:checked+label {
    background: #CDA274;
    color: #FFFFFF;

    padding: 26px 66px;
    border-radius: 18px;
    border: none;
}

input {
    display: none;
}
</style>