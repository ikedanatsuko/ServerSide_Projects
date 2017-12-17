# JavaScript_SPA

## 概要
[Springboot_RESTful_API](https://github.com/utgwn/ServerSide_Projects/tree/master/Springboot_RESTful_API) を叩いて結果を表示する Single Page Application  
<img src="https://github.com/utgwn/ServerSide_Projects/blob/master/JavaScript_SPA/doc/screenshot.png" width="500">  
[画面仕様書](https://github.com/utgwn/ServerSide_Projects/blob/master/JavaScript_SPA/doc/画面仕様書.xlsx)

## 使用技術
- Node.js v8.9.1
- npm v5.6.0
- React.js v16.2.0
- webpack v3.10.0
- Babel
- ES2015 

## 起動方法
1. このリポジトリをダウンロードかCloneする
2. [Springboot_RESTful_API](https://github.com/utgwn/ServerSide_Projects/tree/master/Springboot_RESTful_API) を起動し、認証手順に従って認証
3. 起動
    - Node.jsをインストール
      - 「開発環境のセットアップ手順」を参照
    - カレントディレクトリに移動　例：`cd JavaScript_SPA`
    - カレントディレクトリで次のコマンドを実行：  
    `npm install` （必要なモジュールをインストール）  
    `npm run dev` （サーバー起動）
4. 次のURLにアクセス：`http://localhost:4444`  

## 開発環境のセットアップ手順
- エディタ
  - Atom（1.22.1）をインストール   
- Node.js
  - ndenv
    - Node.jsのバージョンを管理するためにインストール
    - まずanyenvをインストール
    -     $ git clone https://github.com/riywo/anyenv ~/.anyenv
          $ echo 'export PATH="$HOME/.anyenv/bin:$PATH"' >> ~/.zshenv
          $ echo 'eval "$(anyenv init -)"' >> ~/.zshenv
          $ exec $SHELL -l
    - ndenvインストール
    -     $ anyenv install ndenv
          $ exec $SHELL -l
  - node & npm  
    インストール：`ndenv install v8.9.1`  
    インストールしたものを利用するように設定：`$ ndenv global v8.9.1`
    
