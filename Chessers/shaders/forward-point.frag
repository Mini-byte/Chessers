#version 120
#include "lighting.glh"

varying vec3 worldPos0;
varying vec2 texCoord0;
varying vec3 normal0;

uniform sampler2D diffuse;

uniform PointLight pointLight;

void main()
{
	gl_FragColor = texture2D(diffuse, vec2(texCoord0.x, 1 - texCoord0.y)) * calcPointLight(pointLight, normalize(normal0), worldPos0);
}