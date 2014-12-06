#version 330 core

in vec3 Position;
in vec3 Normal;
in vec2 TexCoord;

out vec4 color;

uniform sampler2D ourTexture1;
uniform sampler2D ourTexture2;

uniform float blend;
uniform vec3 viewPos;

struct Material {
    sampler2D diffuse;
    sampler2D specular;
    float shininess;
};

struct DirectionalLight {
    vec3 direction;
  
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

uniform Material material;
uniform DirectionalLight light;

void main()
{
	vec3 norm = normalize(Normal);
	vec3 lightDir = normalize(-light.direction);
	vec3 viewDir = normalize(viewPos - Position);
	vec3 reflectDir = reflect(-lightDir, norm);
	
	float diffuse = max(dot(norm, lightDir), 0.0);
	
	float specular = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
	
	vec3 ambientColor = light.ambient * vec3(texture(material.diffuse, TexCoord));
	vec3 diffuseColor = light.diffuse * diffuse * vec3(texture(material.diffuse, TexCoord));
	vec3 specularColor = light.specular * specular * vec3(texture(material.specular, TexCoord));
	color = vec4(ambientColor + diffuseColor + specularColor, 1.0f);
}