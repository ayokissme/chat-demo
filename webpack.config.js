const webpack = require('webpack')
const path = require('path');
const {VueLoaderPlugin} = require('vue-loader')

module.exports = {
    mode: 'development',
    devtool: 'source-map',
    entry: {
        chat: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'chat.js')
    },
    devServer: {
        static: './dist',
        compress: true,
        port: 8000,
        allowedHosts: [
            'localhost:8081'
        ],
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.css$/,
                use: [
                    'vue-style-loader',
                    'css-loader'
                ]
            },
        ]
    },
    plugins: [
        new VueLoaderPlugin(),
        new webpack.DefinePlugin({
            __VUE_OPTIONS_API__: true,
            __VUE_PROD_DEVTOOLS__: true,
        }),
    ],
    resolve: {
        modules: [
            path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
            path.join(__dirname, 'node_modules'),
        ],
        alias: {
            vue: "vue/dist/vue.esm-bundler.js",
        }
    }
}