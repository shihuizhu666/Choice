<?php

class Choice{

	//当前接口版本
	private $Version = 1.0;

	//接口用户代理
	private $Headers = array();
	private $UserAgent = array();

	//接口网关地址
	private $Gateway = 'http://gateway.shihuizhu.net/open/';

	/**
	* @desc 构造函数
	* @param string  appid
	* @param string  appkey
	* @return void
	*/
	public function __construct( $AppID, $AppKey ){       
		$this->header( $AppID, $AppKey );       
	}

	/**
	* @desc 请求头生成
	* @param string  appid
	* @param string  appkey
	* @return array
	*/
	private function header( $AppID, $AppKey ){    
		$this->UserAgent = array( 'VERSION' => $this->Version, 'APPID' => $AppID, 'APPKEY' => $AppKey );

		$headers = array();    
		foreach( $this->UserAgent as $k => $v ){
			$headers[] = strtoupper( $k ) . ':'. $v;
		}

		return $this->Headers = $headers;    
	}

	/**
	* @desc 调用方法
	* @param string  URL 地址
	* @return string
	*/
	private function fetch( $url ){
		$ch = curl_init();        
		curl_setopt($ch, CURLOPT_HEADER, 0);
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch, CURLOPT_HTTPHEADER, $this->Headers);
		curl_setopt($ch, CURLOPT_TIMEOUT, 20);
		$result = curl_exec($ch);
		curl_close($ch);
		return $result;
	}

	/**
	* @desc 调用方法
	* @param string  方法名称
	* @param string  可选参数
	* @return string
	*/
	public function __call( $func, $opts = array() ){

		$parts = array( $func );
		$param = array();

		foreach( $opts as $k => $v ){        
			if( is_array( $v ) ){
				$param = array_merge( $param, $v );
			}else{
				$parts[] = $v;
			}        
		}

		$route = implode('/', $parts );
		$query = http_build_query( $param );

		$url = $this->Gateway . $route . ( $query ? '?' . $query : '' );

		return $this->fetch( $url );
	}

}