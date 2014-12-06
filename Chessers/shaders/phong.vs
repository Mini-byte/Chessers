#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoord;
layout (location = 2) in vec3 normal;

out vec3 worldPos0;
out vec2 texCoord0;
out vec3 normal0;

uniform mat4 transform;
uniform mat4 projectedTransform;

void main()
{
	gl_Position = projectedTransform * vec4(position, 1);
	
	worldPos0 = (transform * vec4(position, 1.0)).xyz;
	texCoord0 = texCoord;
	normal0 = (transform * vec4(position, 0.0)).xyz;
}