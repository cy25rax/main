<template>
  <header class="header page">
      <div class="header_left">
          <svg width="34" height="34" viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M0 34.0003H13.4588V24.499C13.4588 22.4853 15.0898 20.8543 17.1035 20.8543C19.1172 20.8543 20.7482 22.4853 20.7482 24.499V34.0003H33.9975V0C15.2211 0 0 15.2211 0 34.0003Z" fill="#CDA274"/>
              </svg>
          <a href="index.html">
              <h1 class="header_left_title">
                  Interno
              </h1>
          </a>                    
      </div>
      <nav class="header_right">
          <ul>
              <li>
                  <a href="index.html">Home</a>
                  <a href="project.html">Project</a>
                  <a href="blog.html">Blog</a>
              </li>
          </ul>
      </nav>
  </header>
</template>

<script>
export default {
    name: 'HeaderComp',
};
</script>

<style lang="scss" scoped>

a {
    text-decoration: none;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-top: 61px;
    padding-bottom: 65px;
    
    &_left {
        display: flex;
        gap: 5px;
        align-items: center;

        &_title {
            font-style: normal;
            font-weight: 400;
            font-size: 40px;
            line-height: 125%;
            color: #292F36;
        }
    }

    &_right ul {
        list-style-type: none;

        & li {
            display: flex;
            gap: 30px;

            & a {
                font-family: 'Jost', sans-serif;
                font-style: normal;
                font-weight: 400;
                font-size: 20px;
                line-height: 125%;
                color: #292F36; 
            }
        }
    }
}
</style>