#version 120

attribute vec3 position;
attribute vec2 texCoord;
attribute vec3 normal;

varying vec3 worldPos0;
varying vec2 texCoord0;
varying vec3 normal0;

uniform mat4 model;
uniform mat4 MVP;

void main()
{
	gl_Position = MVP * vec4(position, 1);
	texCoord0 = texCoord;
	worldPos0 = (model * vec4(position, 1.0)).xyz;
	normal0 = (model * vec4(position, 0.0)).xyz;
}