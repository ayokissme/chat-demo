const webpack = require('webpack')
const path = require('path')
const {VueLoaderPlugin} = require('vue-loader')

module.exports = {
    mode: 'development',
    devtool: 'source-map',
    entry: {
        chat: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'chat.js')
    },
    devServer: {
        static: {
            directory: path.join(__dirname, 'public'),
        },
        compress: true,
        port: 8000,
        allowedHosts: [
            'localhost:8081'
        ],
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
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
            __VUE_OPTIONS_API__: false,
            __VUE_PROD_DEVTOOLS__: false,
        }),
    ],
    resolve: {
        modules: [
            path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
            path.join(__dirname, 'node_modules'),
        ],
        alias: {
            vue$: 'vue/dist/vue.esm-bundler.js',
        },
    },
}